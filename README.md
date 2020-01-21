# HouseholdBills

Below we will display the interfaces used, and a quick summary of their functionalities:

<img width="666" alt="menu" src="https://user-images.githubusercontent.com/25267873/72839915-16459100-3c8b-11ea-91dc-426cecf65c5c.png">

The menu interface contains:
* The state of the actual debt for a quick check
* Access to the interface that allows to create a new shop (needs password)
* Access to the interface that allows to pay/borrow money to the other person (needs password)
* Access to the interface that allows to see all money transactions

<img width="666" alt="compra" src="https://user-images.githubusercontent.com/25267873/72839769-cb2b7e00-3c8a-11ea-8f58-aea062a5dd24.png">

The new shop interface contains:
* The name of the shop (e.g. shopping place, products, online website, ...)
* The date of the shop (with a quick access to the date of today and yesterday)
* A comment on what products were bought (or anything really) for more detailed when consulting in the future
* Details of the shop (Total value, Individual value spent by each person, Who paid and how much)

<img width="360" alt="pagamentos" src="https://user-images.githubusercontent.com/25267873/72839916-16459100-3c8b-11ea-85a8-f6b365efd17c.PNG">

Following the shop details above, the algorithm works as follows:
* Didier needs to pay 3.0 + (6.0 - 3.0 - 0.0)/2 - 4.0 = 0.5 to Patrick
* Patrick needs to pay 0.0 + (6.0 - 3.0 - 0.0)/2 - 2.0 = -0.5 to Didier 
    -> Patrick needs to receive 0.5 from Didier.

<img width="666" alt="verContas" src="https://user-images.githubusercontent.com/25267873/72839917-16459100-3c8b-11ea-9e68-11f1364d7929.PNG">

Finally, the money transactions interface contains:
- The date of the shop/payment (year, month, day)
- The details of the shop/payment for further consulting
