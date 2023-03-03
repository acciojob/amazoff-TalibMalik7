package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

   // @Autowired
    OrderRepository repository = new OrderRepository();

     public void addOrder(Order order){
        repository.addOrder(order);
    }
     public void addPartner(String partner){
        repository.addPartner(partner);
    }
     public void addPair(String order,String partner){
        repository.addPair(order,partner);
     }
     public Order get_order(String id){
        return repository.get_order_by_id(id);
    }
     public DeliveryPartner get_partner(String id){
        return repository.get_partner_by_id(id);
    }
     public  Integer order_count(String id){
        return repository.order_count(id);
    }
     public List<String> order_list(String id){
        return  repository.order_list(id);
     }
     public List<String> all_orders(){
       return repository.all_orders();
     }
     public int unassigned_order(){
        return repository.unassigned_order();
     }
     public int getOrdersLeftAfterGivenTimeByPartnerId(String timee, String id){
         String [] time = timee.split(":");
         int time_min = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

       return repository.getOrdersLeftAfterGivenTimeByPartnerId(time_min, id);
     }
     public String last_time(String id){
        return repository.last_time(id);
     }

     public void delete_partner(String id){
         repository.delete_partner(id);
     }
     public void delete_order(String id){
         repository.delete_order(id);
     }

}
