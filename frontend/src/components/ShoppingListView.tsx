import "./ShoppingListView.css"
import {useEffect, useState} from "react";
import axios from "axios";
import {Client} from "../Client.ts";
import {ShoppingListCard} from "./ShoppingListCard.tsx";

export default function ShoppingListView() {

    const [client, setClient] = useState<Client>()

    function fetchClients() {
        axios({
            method: "GET",
            url: "api/store/clients/1",

        })
            .then((response) => {
                setClient(response.data)
            })
    }

    useEffect(() => fetchClients(), []);

    if (!client) {
        return "Lade..."
    }

    return (
        <div className="shoppingListView-container">
            <h2>Shopping Cart</h2>
            <h2>clients</h2>
            {
                client.shoppingList.map((product)=>{return <ShoppingListCard  key={product.id} product={product}/>})
            }
        </div>
    );
};