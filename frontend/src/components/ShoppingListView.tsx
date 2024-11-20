import "./ShoppingListView.css"
import {useEffect, useState} from "react";
import axios from "axios";
import {Client} from "../Client.ts";
import {ShoppingListCard} from "./ShoppingListCard.tsx";



export default function ShoppingListView() {

    const [client, setClient] = useState<Client>()
    const [totalPrice, setTotalPrice] = useState<number>(0)

    function fetchClients() {
        axios({
            method: "GET",
            url: "api/store/clients/1",

        })
            .then((response) => {
                setClient(response.data)
                getTotalPrice(response.data)
            })
    }

    function fetchAllProducts() {
        axios({
            method: "GET",
            url: "api/store/products",

        })
    }



    function addToBill(price: number){

        setTotalPrice(totalPrice +price );
    }

    function getTotalPrice(client: Client){
        client.shoppingList.map(product => {return addToBill(product.price)})
    }





    //useEffect(() => fetchClients(), []);
    useEffect(() => {
        fetchClients();
        fetchAllProducts();
    }, []);

    if (!client) {
        return "Lade..."
    }

    return (
        <div className="clientShoppingListView-container">
            <h2>Shopping Cart</h2>

            <h3>Client: Max Mustermann</h3>
            {
                client.shoppingList.map((product) => {
                    return (
                        <ShoppingListCard key={product.id} product={product} updateList={fetchClients} updateStore={fetchAllProducts}/>
                    )
                })
            }
            <h3>Total Price: {totalPrice}</h3>



</div>
    )
    ;
};