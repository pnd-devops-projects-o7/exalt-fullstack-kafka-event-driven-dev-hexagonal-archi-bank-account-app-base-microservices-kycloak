package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class KafkaEventConsumerService {
    private static final Logger LOGGER = Logger.getLogger(KafkaEventConsumerService.class.getName());
    private final JavaMailSender javaMailSender;
    public KafkaEventConsumerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(groupId = "group1", topicPartitions =
            {
                    @TopicPartition(topic = "${kafka.topics.customer}",
                            partitionOffsets =
                                    {
                                            @PartitionOffset(partition = "0", initialOffset = "0"),
                                            @PartitionOffset(partition = "1", initialOffset = "0"),
                                            @PartitionOffset(partition = "2", initialOffset = "0"),
                                            @PartitionOffset(partition = "3", initialOffset = "0")
                                    })
            })
    public void consumeCustomerEvent(@Payload CustomerEvent customerEvent) {
        LOGGER.log(Level.INFO, "consuming event: {0}", customerEvent);
       MimeMessagePreparator mimeMessagePreparator= prepareAndSendEmail(customerEvent.getCustomer().getEmail(),
               customerEvent.getStatus(), customerEvent);

        javaMailSender.send(mimeMessagePreparator);
        LOGGER.log(Level.INFO, "customer creation email notification is sent");
    }

    @KafkaListener(groupId = "group1", topicPartitions = {
            @TopicPartition(topic = "${kafka.topics.bank-account}", partitionOffsets = {
                    @PartitionOffset(partition = "0",initialOffset = "0"),
                    @PartitionOffset(partition = "1", initialOffset = "0"),
                    @PartitionOffset(partition = "2", initialOffset = "0")
            })
    })
    public void consumerAccountEvent(@Payload BankAccountEvent bankAccountEvent){
        LOGGER.log(Level.INFO,"consume bank account event {0}",bankAccountEvent);
        MimeMessagePreparator mimeMessagePreparator =prepareAndSendEmail(bankAccountEvent.getBankAccount().getCustomer().getEmail(), bankAccountEvent.getStatus(),
                bankAccountEvent);
        javaMailSender.send(mimeMessagePreparator);
    }

    private MimeMessagePreparator prepareAndSendEmail(final String toEmail, final String status, Object object){
        LOGGER.info("prepare notification message to send");
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("no-reply@test.fr");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(String.format("mail to:%s",toEmail));
            mimeMessageHelper.setText(String.format("""
                    Hello,
                    The \s
                                        
                    %s
                     
                    is %s\s
                             
                    Best Regards,
                    The Team
                    """, object, status));
        };
    }
}
