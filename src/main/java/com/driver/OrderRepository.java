package com.driver;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
@Repository
public class OrderRepository {
    HashMap<String,Order> order_map = new HashMap<>();
    HashMap<String,DeliveryPartner> partner_map= new HashMap<>();
    HashMap<String,String> pair = new HashMap<>();
    HashMap<String, List<String>> pair_list = new HashMap<>();

    public OrderRepository(){}

    //1
    public void addOrder(Order order){
        String name = order.getId();
        order_map.put(name,order);
    }
    //2
    public void addPartner(String  id){

        partner_map.put(id,new DeliveryPartner(id));

    }
    //3
    public void addPair(String order, String partner){
        pair.put(order,partner);
        if(pair_list.containsKey(partner)){
            List<String> temp = pair_list.get(partner);
            temp.add(order);
        }
        else{
            List<String> temp = new ArrayList<>();
            temp.add(order);
            pair_list.put(partner,temp);
        }
        DeliveryPartner obj = partner_map.get(partner);
        int no = obj.getNumberOfOrders();
        obj.setNumberOfOrders(no+1);
    }
    //4
    public Order get_order_by_id(String id){
        return order_map.get(id);
    }
    //5
    public DeliveryPartner get_partner_by_id(String id){
        return partner_map.get(id);
    }
   //6
    public Integer order_count(String id){
        return  partner_map.get(id).getNumberOfOrders();
    }
    //7
    public List<String> order_list(String id){
        return pair_list.get(id);
    }
    //8
    public List<String> all_orders(){
        return new ArrayList<>(order_map.keySet());
    }
    //9
    public int unassigned_order(){
        return order_map.size()- pair.size();
    }
    //10
    public int getOrdersLeftAfterGivenTimeByPartnerId(int time, String id){
        List<String> list = pair_list.get(id);
        int count = 0;
        for(String i : list){
            Order obj = order_map.get(i);
            if(obj.getDeliveryTime() > time) count++;
        }
        return count;
    }
    //11

    public String  last_time(String id){

        int temp=0;
        if(pair_list.containsKey(id)){
             temp = order_map.get(pair_list.get(id)).getDeliveryTime();
        }
        String H = "";
        String M = "";
        int hour = temp /60;
        int min = temp % 60;
        H = String.valueOf(hour);
        M = String.valueOf(min);
        if(H.length()<2) H = "0"+H;
        if(M.length()<2) M = "0"+M;
        return H+":"+M;
    }
     // 12
    public void delete_partner(String id){
        if(this.pair_list.containsKey(id)){
            for(String s : pair_list.get(id)){
                if(pair.containsKey(s)){
                    pair.remove(s);
                }
            }
            pair_list.remove(id);
        }
      if(this.partner_map.containsKey(id)){
          this.partner_map.remove(id);
      }
    }
    public void delete_order(String id){
        if(this.order_map.containsKey(id)){
            order_map.remove(id);
        }
        if(pair.containsKey(id)){
            String partner = pair.get(id);
            if(pair_list.containsKey(partner)){
                for(String s : pair_list.get(partner)){
                    if(s.equals(id)){
                        pair_list.get(partner).remove(s);
                    }
                }
            }
            DeliveryPartner obj = partner_map.get(partner);
            obj.setNumberOfOrders(pair_list.get(partner).size());
            pair.remove(id);
        }
    }


}
