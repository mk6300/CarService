My SoftUni Spring Advanced individual project

Application for Car Service Management.
Functionality: Register Users, wich create orders for car reapirs on certain date. Orders are handeled form admin which assign order to reponsible mechianc.
On order date all orders status goes form "Scheduled" to "Pending". When responsible mechianc start work on car, he makes order status "in Progress".
When every order is "in Progress", responsible can mechianc choose every operation "services" he do and add used spare parts to fix the car. When car is fixed and all wanted form customer
services are done, Mechanic leave comment and finish the order. When finished order is on status "Finished" It shows total price of used spare parts and done services in process.
Admin can add "services" wich the car service can do. They have "name", "price" and description.
Service works with Sapare Parts Suppliers wich are choosen form Service. They can be added form Mechaincs and Admins. Every Spare part taken form Supplier is added to "CarService-Parts" 
(other Repository) . From there information for spare parts is used to handle orders and adding information for used spare parts in every order. Addind, editting and deletind spare parts can be done 
form Mechaincs and Admins.
Admin can manage users roles and delete users. Admin can make and remuve Mechiancs and Admins
