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

         return repository.get_order_by_id(id);
    }
     public DeliveryPartner getPartnerById(String id){

         return repository.get_partner_by_id(id);
    }
     public  Integer getOrderCountByPartnerId(String id){

         return repository.order_count(id);
    }
     public List<String> getOrdersByPartnerId(String id){

         return  repository.order_list(id);
     }
     public List<String> getAllOrders(){

         return repository.all_orders();
     }
     public int getCountOfUnassignedOrders(){

         return repository.unassigned_order();
     }
     public Integer getOrdersLeftAfterGivenTimeByPartnerId(String timee, String id){

       return repository.getOrdersLeftAfterGivenTimeByPartnerId(timee, id);
     }
     public String getLastDeliveryTimeByPartnerId(String id){

         return repository.last_time(id);
     }

     public void deletePartnerById(String id){

         repository.delete_partner(id);
     }
     public void deleteOrderById(String id){

         repository.delete_order(id);
     }

}
