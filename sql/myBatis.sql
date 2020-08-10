SELECT 
				      oi.order_no order_no
				    , oi.order_id order_id
				    , oi.order_dt order_dt
				    , ol.order_prod_no prod_no
			  	    , ol.order_quantity  order_quantity 
				    , p.prod_name prod_name
				    , p.prod_price prod_price
				    , p.prod_price * ol.order_quantity as total
	      FROM order_info oi
		    JOIN order_line ol
		       ON oi.order_no = ol.order_no
		    JOIN product p 
		       ON ol.order_prod_no = p.prod_no
		WHERE 1=1
			oi.order_id = 'id5'