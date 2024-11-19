import "./ShoppingListCard.css"
//import {Client} from "../Client.ts";
import {Product} from "../Product.ts";
import axios from "axios";
import {useState} from "react";


type Props = {
    product: Product
    update: ()=>void;
}

export function ShoppingListCard(props: Props) {

    const [count, setCount] = useState(props.product.count)

    function deleteProductFromCart() {
        console.log(props.product.name + " deleted")
/*        axios({
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
            });*/

        let newCount: number = count - 1;
        if(newCount<0){
            newCount = 0;
        }

        setCount(newCount)

        axios.put("/api/store/clients/1/shoppingList/"+props.product.id, {
            ...props.product,
            count: newCount,
        } as Product)
            .then(response => {
                console.log("Product count in cart updated successfully:", response.data);
            })
            .then(props.update)
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