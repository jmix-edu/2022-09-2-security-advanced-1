package com.company.jmixpm.security;

import com.company.jmixpm.entity.Notification;
import io.jmix.core.UnconstrainedDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;

    @PersistenceContext
    private EntityManager entityManager;

    public void markAsRead(Notification notification) {
        notification = unconstrainedDataManager.load(Notification.class)
                .id(notification.getId())
                .one();

        notification.setIsRead(true);

        unconstrainedDataManager.save(notification);
    }

    @Transactional
    public void markAsReadEm(Notification notification) {
        notification = entityManager.find(Notification.class, notification.getId());

        notification.setIsRead(true);
    }
}