import "./ShoppingListCard.css"
//import {Client} from "../Client.ts";
import {Product} from "../Product.ts";

type Props = {
    product: Product
}

export function ShoppingListCard(props: Props) {
    return (
        <div className="shoppingListCard-container">
            <p>{props.product.name}</p>
            <img src={`/${props.product.image}`} alt="image of product"/>
            <p>Category: {props.product.category}</p>
            <p>Price: {props.product.price} euro</p>
            <p>Count: {props.product.count}</p>
            <p>Status: {props.product.status}</p>


        </div>
    );
};