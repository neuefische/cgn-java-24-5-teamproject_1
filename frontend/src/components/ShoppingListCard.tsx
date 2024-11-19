import "./ShoppingListCard.css"
//import {Client} from "../Client.ts";
import {Product} from "../Product.ts";
import axios from "axios";


type Props = {
    product: Product
}

export function ShoppingListCard(props: Props) {

    function deleteProductFromCart() {
        console.log(props.product.name + " deleted")
        axios({
            method: "PUT",
            url: `api/store/clients/1/shoppingList/${props.product.id}`,
            data: {
                count: props.product.count, // The new count value for the product
            },
        })
            .then(response => {
                console.log("Product count updated successfully:", response.data);
            })
            .catch(error => {
                console.error("Error updating product count:", error);
            });
    }

    return (
        <div className="shoppingListCard-container" onClick={deleteProductFromCart}>
            <p>{props.product.name}</p>
            <img src={`/${props.product.image}`} alt="image of product"/>
            <p>Category: {props.product.category}</p>
            <p>Price: {props.product.price} euro</p>
            <p>Count: {props.product.count}</p>
            <p>Status: {props.product.status}</p>


        </div>
    );
};