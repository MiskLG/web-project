package service;

import beans.Subscription;
import repository.SubscriptionRepository;

import java.util.ArrayList;

public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionService() {
        subscriptionRepository = SubscriptionRepository.init();
    }

    public void add(Subscription subscription) {
        subscriptionRepository.add(subscription);
    }

    public ArrayList<Subscription> getAll() {
        return subscriptionRepository.getAll();
    }

    public Subscription getById(String id) {
        for (Subscription subscription: subscriptionRepository.getAll()) {
            if (subscription.getId().equalsIgnoreCase(id)) {
                return subscription;
            }
        }
        return null;
    }
}
