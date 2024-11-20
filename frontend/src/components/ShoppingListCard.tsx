import "./ShoppingListCard.css"
//import {Client} from "../Client.ts";
import {Product} from "../Product.ts";
import axios from "axios";
import {useState} from "react";


type Props = {
    product: Product
    updateList: ()=>void;
    updateStore: ()=>void;
}

export function ShoppingListCard(props: Props) {

    const [count, setCount] = useState(props.product.count)

    function deleteProductFromCart() {
        console.log(props.product.name + " deleted")


        let newCount: number = count - 1;
        if(newCount<0){
            newCount = 0;
        }

        setCount(newCount)


        if(count==0){
            axios.delete("/api/store/clients/1/shoppingList/"+props.product.id,)
                .then(response => {
                    console.log("Product deleted from cart successfully:", response.data);
                    console.log("/api/store/clients/1/shoppingList/"+props.product.id);
                })
                .then(props.updateList)
                .catch(error => {
                    console.error("Error updating product count:", error);
                });
        }
        else{
            axios.put("/api/store/clients/1/shoppingList/"+props.product.id, {
                ...props.product,
                count: newCount,
            } as Product)
                .then(response => {
                    console.log("Product count in cart updated successfully:", response.data);
                })
                .then(props.updateList)
                .catch(error => {
                    console.error("Error updating product count:", error);
                });
        }




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