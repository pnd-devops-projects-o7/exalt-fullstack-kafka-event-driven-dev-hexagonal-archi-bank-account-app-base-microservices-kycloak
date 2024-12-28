package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class KafkaEventConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventConsumerService.class.getName());
    private final JavaMailSender javaMailSender;
    @KafkaListener(groupId = "group1", topicPartitions =
            {
                    @TopicPartition(topic = "${kafka.topics.topic1}",
                            partitionOffsets =
                                    {
                                            @PartitionOffset(partition = "0", initialOffset = "0"),
                                            @PartitionOffset(partition = "1", initialOffset = "0"),
                                            @PartitionOffset(partition = "2", initialOffset = "0"),
                                            @PartitionOffset(partition = "3", initialOffset = "0")
                                    })
            })
    public void consumeCustomerEvent(@Payload CustomerEvent customerEvent)  {
        LOGGER.debug( "consuming event: {}", customerEvent);
        MimeMessagePreparator mimeMessagePreparator = prepareAndSendEmail(customerEvent.getCustomer().getEmail(),
                customerEvent.getStatus(), customerEvent);
        LOGGER.debug("customer creation email notification is sent");
        javaMailSender.send(mimeMessagePreparator);

    }

    @KafkaListener(groupId = "group1", topicPartitions = {
            @TopicPartition(topic = "${kafka.topics.topic2}", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0"),
                    @PartitionOffset(partition = "1", initialOffset = "0"),
                    @PartitionOffset(partition = "2", initialOffset = "0"),
                    @PartitionOffset(partition = "3", initialOffset = "0"),
                    @PartitionOffset(partition = "4", initialOffset = "0"),
                    @PartitionOffset(partition = "5", initialOffset = "0")
            })
    })
    public void consumerAccountEvent(@Payload BankAccountEvent bankAccountEvent) {
        LOGGER.debug("consume bank account event {}", bankAccountEvent);
        MimeMessagePreparator mimeMessagePreparator = prepareAndSendEmail(bankAccountEvent.getBankAccount().getCustomer().getEmail(),
                bankAccountEvent.getStatus(),
                bankAccountEvent);
        javaMailSender.send(mimeMessagePreparator);
    }

    @KafkaListener(groupId = "group1", topicPartitions = {
            @TopicPartition(topic = "${kafka.topics.topic3}", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0")
            }),
            @TopicPartition(topic = "${kafka.topics.topic3}", partitionOffsets = {
                    @PartitionOffset(partition = "0",initialOffset = "0")
            })
    })
    public void consumerOperationEvent(@Payload OperationEvent operationEvent, @Payload TransferEvent transferEvent) {
        LOGGER.debug( "consume operation {} and transfer {} payloads", operationEvent, transferEvent);
        MimeMessagePreparator operationMimeMessagePreparator = prepareAndSendEmail(
                operationEvent.getOperation().getBankAccount().getCustomer().getEmail(), operationEvent.getStatus(), operationEvent);
        javaMailSender.send(operationMimeMessagePreparator);

        MimeMessagePreparator transferMimeMessagePreparator = prepareAndSendEmail(
                transferEvent.getTransfer().getOriginAccount().getOriginCustomer().getEmail(),
                transferEvent.getStatus(), transferEvent);
        javaMailSender.send(transferMimeMessagePreparator);
    }

    private MimeMessagePreparator prepareAndSendEmail(final String toEmail, final String status, Object object) {
        LOGGER.info("prepare notification message to send");
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("no-reply@test.fr");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(String.format("mail to:%s", toEmail));
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
