package com.exalt.exalthexagonalarchikafkakeycloaknotificationapi.service;

import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account.BankAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbankaccountapi.domain.avro_beans.account_activated.ActiveAccountEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.operations.OperationEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmsoperationapi.domain.avro_beans.transfers.TransferEvent;
import com.exalt.exalthexagonalarchikafkakeycloakcustomerapi.domain.avro_beans.CustomerEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private static final Logger log = LoggerFactory.getLogger(EmailSenderServiceImpl.class.getName());
    @Value("${notification.mail.sender.ip-address}")
    private String mailhogServerIpAddress;
    @Value("${notification.mail.sender.port1}")
    private int mailhogServerPort;
    @Value("${notification.mail.sender.user}")
    private String mailhogUsername;
    @Value("${notification.mail.sender.password}")
    private String mailhogPwd;
    @Value("${notification.mail.sender.protocol}")
    private String protocol;

    //gmail sender provider params
    @Value("${spring.mail.host}")
    private String gmailHostname;
    @Value("${spring.mail.port}")
    private int gmailPort;
    @Value("${spring.mail.username}")
    private String gmailUsername;
    @Value("${spring.mail.password}")
    private String gmailPassword;

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

        //mailhog server sender
        JavaMailSenderImpl mailHogSender = mailSenderWithMailHogProvider();
        MimeMessage mailHogMimeMessage = prepareMimeMessage(mailHogSender, customerEvent.getCustomer().getEmail(),
                customerEvent, customerEvent.getStatus());
        mailHogSender.send(mailHogMimeMessage);

        //gmail server sender
        JavaMailSenderImpl gmailSenderProvider = mailSenderWithGmailProvider();
        MimeMessage gmailMimeMessage = prepareMimeMessage(gmailSenderProvider, customerEvent.getCustomer().getEmail(),
                customerEvent, customerEvent.getStatus());
        gmailSenderProvider.send(gmailMimeMessage);
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

        //mailhog server sender
        JavaMailSenderImpl mailHogSender = mailSenderWithMailHogProvider();
        MimeMessage mailhogMimeMessage = prepareMimeMessage(mailHogSender, bankAccountEvent.getBankAccount().getCustomer().getEmail()
                , bankAccountEvent, bankAccountEvent.getStatus());
        mailHogSender.send(mailhogMimeMessage);

        //gmail server sender
        JavaMailSenderImpl gmailSenderProvider = mailSenderWithGmailProvider();
        MimeMessage gmailMimeMessage = prepareMimeMessage(gmailSenderProvider, bankAccountEvent.getBankAccount().getCustomer().getEmail()
                , bankAccountEvent, bankAccountEvent.getStatus());
        gmailSenderProvider.send(gmailMimeMessage);


    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions =
            {@TopicPartition(topic = "${kafka.topics.topic3}",
                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0"))})
    public void sendActivateAccountNotificationEmail(ActiveAccountEvent activeAccountEvent) throws MessagingException {
        log.debug("consuming activate account event: {}", activeAccountEvent);

        //mailhog server sender
        JavaMailSenderImpl mailHogSender = mailSenderWithMailHogProvider();
        MimeMessage mailHogMimeMessage = prepareMimeMessage(
                mailHogSender, "activated.account@test.fr", activeAccountEvent, activeAccountEvent.getStatus());
        mailHogSender.send(mailHogMimeMessage);

        //gmail server sender
        JavaMailSenderImpl gmailSenderProvider = mailSenderWithGmailProvider();
        MimeMessage gmailMimeMessage = prepareMimeMessage(gmailSenderProvider,
                "activated.account@test.fr", activeAccountEvent, activeAccountEvent.getStatus());
        gmailSenderProvider.send(gmailMimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions = {@TopicPartition(topic = "${kafka.topics.topic4}",
            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")})})
    public void sendOperationNotificationEmail(OperationEvent operationEvent) throws MessagingException {
        log.debug("consuming operation {}", operationEvent);

        //mailhog server sender
        JavaMailSenderImpl mailHogSender = mailSenderWithMailHogProvider();
        MimeMessage mailHogMimeMessage = prepareMimeMessage(mailHogSender, operationEvent.getOperation()
                .getBankAccount().getCustomer().getEmail(), operationEvent, operationEvent.getStatus());
        mailHogSender.send(mailHogMimeMessage);

        //gmail server sender
        JavaMailSenderImpl gmailSenderProvider = mailSenderWithGmailProvider();
        MimeMessage gmailMimeMessage = prepareMimeMessage(gmailSenderProvider, operationEvent.getOperation()
                .getBankAccount().getCustomer().getEmail(), operationEvent, operationEvent.getStatus());
        gmailSenderProvider.send(gmailMimeMessage);
    }

    @Override
    @KafkaListener(groupId = "group1", topicPartitions = {@TopicPartition(topic = "${kafka.topics.topic5}",
            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")})})
    public void sendTransferNotificationEmail(TransferEvent transferEvent) throws MessagingException {
        log.debug("consuming transfer event {}", transferEvent);

        //mailhog server sender
        JavaMailSenderImpl mailHogSender = mailSenderWithMailHogProvider();
        MimeMessage mailHogMimeMessage = prepareMimeMessage(mailHogSender,
                transferEvent.getTransfer().getOriginAccount()
                        .getOriginCustomer().getEmail(), transferEvent, transferEvent.getStatus());
        mailHogSender.send(mailHogMimeMessage);

        //gmail server sender
        JavaMailSenderImpl gmailSender = mailSenderWithGmailProvider();
        MimeMessage gmailMimeMessage = prepareMimeMessage(gmailSender, transferEvent.getTransfer().getOriginAccount()
                .getOriginCustomer().getEmail(), transferEvent, transferEvent.getStatus());
        gmailSender.send(gmailMimeMessage);
    }

    //using mail hog mail sender as provider
    private JavaMailSenderImpl mailSenderWithMailHogProvider() {
        return setMailSenderHostParams(mailhogServerIpAddress, mailhogServerPort, mailhogUsername, mailhogPwd);
    }

    //using gmail mail sender as provider
    private JavaMailSenderImpl mailSenderWithGmailProvider() {
        return setMailSenderHostParams(gmailHostname, gmailPort, gmailUsername, gmailPassword);
    }

    private JavaMailSenderImpl setMailSenderHostParams(String host, int port, String username, String password) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        // we configure javaMailSender with other properties
        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return javaMailSender;
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
                \s
                The Team
                """, event, status));

        return mimeMessage;
    }

}
