
docker build bank-service/ -t leonahoms/bankservice && docker push leonahoms/bankservice

docker build account-service/ -t leonahoms/accountservice && docker push leonahoms/accountservice

docker build gateway-service/ -t leonahoms/gatewayservice && docker push leonahoms/gatewayservice

docker build product-service/ -t leonahoms/productservice && docker push leonahoms/productservice

docker build stock-service/ -t leonahoms/stockservice && docker push leonahoms/stockservice

docker build  order-service/ -t leonahoms/orderservice && docker push leonahoms/orderservice

docker build  paypal-service/ -t leonahoms/paypalservice && docker push leonahoms/paypalservice

docker build  payment-service/ -t leonahoms/paymentservice && docker push leonahoms/paymentservice
