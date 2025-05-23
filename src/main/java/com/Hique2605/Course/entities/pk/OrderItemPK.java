package com.Hique2605.Course.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.Hique2605.Course.entities.Order;
import com.Hique2605.Course.entities.Vinho;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK implements Serializable{

	private static final long serialVersionUID = 1L;
		
		@ManyToOne
		@JoinColumn(name = "order_id")
		private Order order;
		
		@ManyToOne
		@JoinColumn(name = "vinho_id")
		private Vinho vinho;
		
		public Order getOrder() {
			return order;
		}
		public void setOrder(Order order) {
			this.order = order;
		}
		public Vinho getVinho() {
			return vinho;
		}
		public void setVinho(Vinho vinho) {
			this.vinho = vinho;
		}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		OrderItemPK that = (OrderItemPK) o;
		return Objects.equals(order, that.order) && Objects.equals(vinho, that.vinho);
	}

	@Override
	public int hashCode() {
		return Objects.hash(order, vinho);
	}
}
