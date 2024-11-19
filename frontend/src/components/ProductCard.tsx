import "./ProductCard.css"
import {Product} from "../Product.ts";

type Props = {
    product: Product;
}

export default function ProductCard(props : Props) {

    function addProductToCart() {
        console.log(props.product.name + " added")


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