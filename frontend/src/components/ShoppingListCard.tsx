import "./ShoppingListCard.css"
//import {Client} from "../Client.ts";
import {Product} from "../Product.ts";

type Props = {
    product: Product
}

export function ShoppingListCard(props: Props) {
    return (
        <div>
            <p>{props.product.name}</p>
        </div>
    );
};