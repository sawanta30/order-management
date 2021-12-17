package com.oms.manage.entity;

import com.oms.manage.enums.AddressType;
import com.oms.manage.enums.OrderStatus;
import com.oms.manage.enums.OrderType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private long orderId;

    @Column(name = "orderType")
    private OrderType orderType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "orderDate")
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expDeliveryDate")
    private Date expDeliveryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deliveredDate")
    private Date deliveredDate;

    @Column(name = "orderStatus")
    private OrderStatus orderStatus;
    @Column(name = "orderAddress")
    private String orderAddress;
    @Column(name = "addressType")
    private AddressType addressType;
    @Column(name = "orderDetails")
    private String orderDetails;
    @Column(name = "isPrepaid")
    private boolean isPrepaid;
    @Column(name = "billingStatus")
    private BillingStatus billingStatus;
    @Column(name = "isActive")
    private boolean isActive;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpDeliveryDate() {
        return expDeliveryDate;
    }

    public void setExpDeliveryDate(Date expDeliveryDate) {
        this.expDeliveryDate = expDeliveryDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public boolean isPrepaid() {
        return isPrepaid;
    }

    public void setPrepaid(boolean prepaid) {
        isPrepaid = prepaid;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
