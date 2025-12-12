# Product Service

**Manages product catalog and stock**
- CRUD on products
- Check stock availability
- Reduce stock after order

| Method     | Endpoint                                       | Description           | Input                | Output          |
| ---------- | ---------------------------------------------- | --------------------- | -------------------- | --------------- |
| **GET**    | `/products/{id}`                               | Fetch a product by ID | Path variable (`id`) | Product JSON    |
| **POST**   | `/products` *(optional CRUD)*                  | Create a new product  | Product JSON         | Saved Product   |
| **PUT**    | `/products/{id}` *(optional)*                  | Update product        | Product JSON         | Updated Product |
| **DELETE** | `/products/{id}` *(optional)*                  | Delete product        | Path variable        | void            |
| **POST**   | `/products/reserve-stock?productId=&quantity=` | Check + reserve stock | Request params       | `true/false`    |
