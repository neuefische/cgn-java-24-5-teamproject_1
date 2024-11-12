import "./ProductView.css"
import ProductCard from "./ProductCard.tsx";
import axios from "axios";
import {useEffect, useState} from "react";
import {Product} from "../Product.ts";

export default function ProductView() {

    const [products,setProducts] = useState<Product[]>([])

    function fetchAllProducts(){
        axios({
            method:"GET",
            url:"api/store",

        })
            .then((response)=>{setProducts(response.data)})
    }

    useEffect(() => {
        fetchAllProducts();
    }, []);

    return (
        <div>
            <p>Products : </p>
            {
            products.map((product)=>{return <ProductCard product={product} key={product.id}/>})
            }
        </div>
    );
};