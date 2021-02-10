package com.acme.testing;

import java.time.LocalDate;
import com.acme.domain.Good.UnitOfMeasureType;
import com.acme.domain.Order;
import com.acme.domain.Solid;
import com.acme.utils.MyDate;
import com.acme.domain.Service;

public class TestOrders {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MyDate date1 = new MyDate(1, 20, 2008);
		Solid s1 = new Solid("Acme Anvil", 1668, 0.3, UnitOfMeasureType.CUBIC_METER, false, 500, 0.25, 0.3);
		Order anvil = new Order(date1, 2000.00, "Wile E Coyote", s1, 10);

		MyDate date2 = new MyDate(4, 10, 2008);
		Solid s2 = new Solid("Acme Balloon", 1401, 15, UnitOfMeasureType.CUBIC_FEET, false, 10, 5, 5);
		Order balloons = new Order(date2, 1000.00, "Bugs Bunny", s2, 125);

		balloons.setQuantity(-200);

		System.out.println(anvil);
		System.out.println(balloons);

		System.out.println("The tax Rate is currently: " + Order.getTaxRate());
		Order.computeTaxOn(3000.00);
		anvil.computeTax();
		balloons.computeTax();
		Order.setTaxRate(0.06);
		System.out.println("The tax Rate is currently: " + Order.getTaxRate());
		Order.computeTaxOn(3000.00);
		anvil.computeTax();
		balloons.computeTax();

		System.out.println("The total bill for: " + anvil + " is " + anvil.computeTotal());
		System.out.println("The total bill for: " + balloons + " is " + balloons.computeTotal());

//		MyDate date3 = new MyDate(4, 10, 2008);
		MyDate date3 = new MyDate(1, 1, 2016);
		Service s3 = new Service("Road Runner Eradication", 14, false);
		Order birdEradication = new Order(date3, 20000, "Daffy Duck", s3, 1);
		System.out.println("The total bill for: " + birdEradication + " is " + birdEradication.computeTotal());

		Order.setRushable((orderDate, orderAmount) -> orderAmount > 1500);

		System.out.println("Anvil isPriorityOrder: " + anvil.isPriorityOrder());
		System.out.println("Balloons isPriorityOrder: " + balloons.isPriorityOrder());

		MyDate hammerDate = new MyDate(1, 25, 2021);
		Solid hammerType = new Solid("Acme Hammer", 281, 0.3, UnitOfMeasureType.CUBIC_METER, false, 100, 0.25, 0.3);
		Order hammer = new Order(hammerDate, 10.00, "Wile E Coyote", hammerType, 10);
		Order.setRushable((orderDate, orderAmount) -> {
			LocalDate now = LocalDate.now();
			LocalDate orderDatePlus30 = LocalDate.of(orderDate.getYear(), orderDate.getMonth(), orderDate.getDay());
			orderDatePlus30 = orderDatePlus30.plusMonths(1);

			return now.isAfter(orderDatePlus30);
		});

		System.out.println("Anvil isPriorityOrder: " + anvil.isPriorityOrder());
		System.out.println("Hammer isPriorityOrder: " + hammer.isPriorityOrder());

		// BONUS LAB: POLYMORPHISM
		// System.out.println("The volume of the anvil is: " +
		// anvil.getProduct().volume());
		// System.out.println("The length of the anvil is: " + ((Solid)
		// anvil.getProduct()).getLength());
		

		

	}

}
