import "./ProductCard.css"
import {Product} from "../Product.ts";

type Props = {
    product: Product;
}

export default function ProductCard(props : Props) {
    return (
        <div>
            <p>product</p>

            <p>"Id: "{props.product.id}</p>
            <p>"Name: "{props.product.name}</p>
            <p>"Category: "{props.product.category}</p>
            <p>"Price: "{props.product.price}</p>

        </div>
    )
}