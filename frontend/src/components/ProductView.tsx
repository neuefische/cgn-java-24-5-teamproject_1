import "./ProductView.css"
import ProductCard from "./ProductCard.tsx";
import axios from "axios";
import {useEffect, useState} from "react";
import {Product} from "../Product.ts";
import SearchBar from "./SearchBar.tsx"

export default function ProductView() {

    const [products, setProducts] = useState<Product[]>([]);
    const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);

    function fetchAllProducts() {
        axios({
            method: "GET",
            url: "api/store",

        })
            .then((response) => {
                setProducts(response.data);
                setFilteredProducts(response.data);
            });
    }

    const filterProducts = (query: string) => {
        if (query.trim() === "") {
            setFilteredProducts(products);
        } else {
            const lowercasedQuery = query.toLowerCase();
            const filtered = products.filter((product) =>
                product.name.toLowerCase().startsWith(lowercasedQuery)
            );
            setFilteredProducts(filtered);
        }
    };

    useEffect(() => {
        fetchAllProducts();
    }, []);

    return (
        <div className="productView-container">
            <h2>Products</h2>
            <SearchBar onSearch={filterProducts}/> {/* Add the SearchBar component */}
            <div className="productList-container">
                {filteredProducts.map((product) => {
                    return <ProductCard product={product} key={product.id}/>;
                })}
            </div>
        </div>
    );
}