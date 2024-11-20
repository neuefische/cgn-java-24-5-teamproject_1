import "./ProductCard.css"
import {Product} from "../Product.ts";
import axios from "axios";
import {useState} from "react";
//import {useState} from "react";

type Props = {
    product: Product;
    update: ()=>void;
    updateCart: ()=>void;
    //shoppingList: Product[];
}

export default function ProductCard(props : Props) {

    //const [product, setProduct] = useState<Product>();
    const [count, setCount] = useState(props.product.count)

    function addProductToCart() {


        // reduce count in store
        let newCount: number = count - 1;
        if(newCount<0){
            newCount = 0;
        }

        setCount(newCount)

        console.log(props.product.name + " added" + newCount)


        axios.put("/api/store/products/"+props.product.id, {
            ...props.product,
            count: newCount,
        } as Product)
            .then(response => {
                console.log("Product count in store updated successfully:", response.data);
            })
            .then(props.update)
            .catch(error => {
                console.error("Error updating product count:", error);
            });

        // add product to shopping list if not exists otherwise increase count

        axios.post("/api/store/clients/1/shoppingList", props.product)
            .then(response => {
                console.log("Product added to cart successfully:", response.data);
            })
            .then(props.updateCart)
            .catch(error => {
                console.error("Error updating cart:", error);
            });


    }








    return (
        <div className="productCard-container" onClick={addProductToCart}>


            <h3>{props.product.name}</h3>
            <p>Category: {props.product.category}</p>
            <p>Price: {props.product.price} euro</p>
            <p>Count: {props.product.count} </p>
            <img src={`/${props.product.image}`} alt="image of product"/>


        </div>
    )
}