package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class.getName());
    @Value("${notification.mail.sender.ip-address}")
    private String mailServerHostIpAddress;
    @Value("${notification.mail.sender.port1}")
    private int mailServerPort;
    @Value("${notification.mail.sender.user}")
    private String username;
    @Value("${notification.mail.sender.password}")
    private String pwd;
    @Value("${notification.mail.sender.protocol}")
    private String protocol;

    @Override
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
    public void sendCustomerNotificationEmail(CustomerEvent customerEvent) throws MessagingException {
        log.debug("consuming customer event: {}", customerEvent);
        JavaMailSenderImpl javaMailSender = javaMailSender();
        MimeMessage mimeMessage = prepareMimeMessage(javaMailSender, customerEvent.getCustomer().getEmail(),
                customerEvent, customerEvent.getStatus());
        javaMailSender.send(mimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions =
            {
                    @TopicPartition(topic = "${kafka.topics.topic2}",
                            partitionOffsets =
                                    {
                                            @PartitionOffset(partition = "0", initialOffset = "0"),
                                            @PartitionOffset(partition = "1", initialOffset = "0"),
                                            @PartitionOffset(partition = "2", initialOffset = "0"),
                                            @PartitionOffset(partition = "3", initialOffset = "0"),
                                    })
            })
    public void sendAccountNotificationEmail(BankAccountEvent bankAccountEvent) throws MessagingException {
        log.error("consuming account event {}", bankAccountEvent);
        JavaMailSenderImpl javaMailSender = javaMailSender();
        MimeMessage mimeMessage = prepareMimeMessage(javaMailSender, bankAccountEvent.getBankAccount().getCustomer().getEmail()
                , bankAccountEvent, bankAccountEvent.getStatus());
        javaMailSender.send(mimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions =
            {@TopicPartition(topic = "${kafka.topics.topic3}",
                    partitionOffsets =@PartitionOffset(partition = "0", initialOffset = "0"))})
    public void sendActivateAccountNotificationEmail(ActiveAccountEvent activeAccountEvent) throws MessagingException {
        log.debug("consuming activate account event: {}", activeAccountEvent);
        JavaMailSenderImpl javaMailSender = javaMailSender();
        MimeMessage mimeMessage = prepareMimeMessage(
                javaMailSender,"activated.account@test.fr",activeAccountEvent,activeAccountEvent.getStatus());
        javaMailSender.send(mimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions = {@TopicPartition(topic = "${kafka.topics.topic4}",
            partitionOffsets = { @PartitionOffset(partition = "0", initialOffset = "0")})})
    public void sendOperationNotificationEmail(OperationEvent operationEvent) throws MessagingException {
        log.debug("consuming operation {}", operationEvent);
        JavaMailSenderImpl javaMailSender = javaMailSender();
        MimeMessage operationMimeMessage = prepareMimeMessage(javaMailSender, operationEvent.getOperation()
                .getBankAccount().getCustomer().getEmail(), operationEvent, operationEvent.getStatus());
        javaMailSender.send(operationMimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions = {@TopicPartition(topic = "${kafka.topics.topic5}",
            partitionOffsets = { @PartitionOffset(partition = "0", initialOffset = "0")})})
    public void sendTransferNotificationEmail(TransferEvent transferEvent) throws MessagingException {
        log.debug("consuming transfer event {}", transferEvent);
        JavaMailSenderImpl javaMailSender = javaMailSender();
        MimeMessage transferMimeMessage = prepareMimeMessage(javaMailSender,
                transferEvent.getTransfer().getOriginAccount()
                        .getOriginCustomer().getEmail(), transferEvent, transferEvent.getStatus());
        javaMailSender.send(transferMimeMessage);
    }

    private JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(mailServerHostIpAddress);
        javaMailSenderImpl.setPort(mailServerPort);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(pwd);
        // we configure javaMailSender with other properties
        Properties pros = javaMailSenderImpl.getJavaMailProperties();
        pros.put("mail.transport.protocol", protocol);
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.debug", "true");
        return javaMailSenderImpl;
    }

    private MimeMessage prepareMimeMessage(JavaMailSenderImpl javaMailSender, final String toEmail,
                                           Object event, final String status) throws MessagingException {
        log.info("prepare notification message to send");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("no-reply@home.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(String.format("message to:%s", toEmail));
        mimeMessageHelper.setText(String.format("""
                Hello,
                \s
                %s
                is %s\s
                Best Regards,
                The Team
                """, event, status));

        return mimeMessage;
    }
}
