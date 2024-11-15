import "./ShoppingListCard.css"
import {Product} from "../Product.ts";

type Props = {
    product: Product
}

export function ShoppingListCard(props: Props) {
    return (
        <div>
            <p>{props.product.id} - {props.product.name} - {props.product.category} - {props.product.price}</p>
        </div>
    );
};