import "./ShoppingListView.css"
import {useEffect, useState} from "react";
import axios from "axios";
import {Client} from "../Client.ts";
import {ShoppingListCard} from "./ShoppingListCard.tsx";

export default function ShoppingListView() {

    const [client, setClient] = useState<Client[]>([])

    function fetchClientById() {
        axios({
            method: "GET",
            url: "api/store/clients/1",

        })
            .then((response) => {
                setClient(response.data)
            })
    }

    useEffect(() => fetchClientById, []);

    if (!client) {
        return "Lade..."
    }

    return (
        <div className="shoppingListView-container">
            <h2>Shopping Cart</h2>
            <h2>clients</h2>
            <div className="shoppingList-container">
                {

                    client[0].shoppingList.map((product) => <ShoppingListCard key={product.id} product={product}/>)
                }
            </div>
        </div>
    );
};