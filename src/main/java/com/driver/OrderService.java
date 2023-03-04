package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository ;

    public void addOrder(Order order){

        repository.addOrder(order);
    }
    public void addPartner(String partner){

        repository.addPartner(partner);
    }
    public void addOrderPartnerPair(String order,String partner){

        repository.addOrderPartnerPair(order,partner);
    }
    public Order getOrderById(String id){

        return repository.getOrderById(id);
    }
    public DeliveryPartner getPartnerById(String id){

        return repository.getPartnerById(id);
    }
    public  Integer getOrderCountByPartnerId(String id){

        return repository.getOrderCountByPartnerId(id);
    }
    public List<String> getOrdersByPartnerId(String id){

        return  repository.getOrdersByPartnerId(id);
    }
    public List<String> getAllOrders(){

        return repository.getAllOrders();
    }
    public int getCountOfUnassignedOrders(){

        return repository.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String timee, String id){

        return repository.getOrdersLeftAfterGivenTimeByPartnerId(timee, id);
    }
    public String getLastDeliveryTimeByPartnerId(String id){

        return repository.getLastDeliveryTimeByPartnerId(id);
    }

    public void deletePartnerById(String id){

        repository.deletePartnerById(id);
    }
    public void deleteOrderById(String id){

        repository.deleteOrderById(id);
    }

}