package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;

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
public class CustomerEventConsumerService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final JavaMailSender javaMailSender;

    public CustomerEventConsumerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @KafkaListener(groupId = "group1",topicPartitions = @TopicPartition(topic = "t_customer",
            partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0"),
                    @PartitionOffset(partition = "1", initialOffset = "0"),
                    @PartitionOffset(partition = "2", initialOffset = "0"),
            }))
    public void consumeCustomerEvent(@Payload CustomerEvent customerEvent) {
        logger.log(Level.INFO, "consuming event: {0}", customerEvent);
        final String status = customerEvent.getStatus();
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("random-email@email.com");
            mimeMessageHelper.setTo(customerEvent.getCustomer().getEmail());
            mimeMessageHelper.setSubject(String.format("customer %s is %s successfully",
                    customerEvent.getCustomer().getEmail(), status));
            mimeMessageHelper.setText(String.format("""
                    Hello,
                    The customer\s
                                        
                    %s
                     
                    is %s\s
                             
                    Best Regards,
                    The Team
                    """, customerEvent, status));

        };
        javaMailSender.send(mimeMessagePreparator);
        logger.log(Level.INFO, "customer creation email notification is sent");
    }
}
