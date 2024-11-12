import {Category} from "./Category.ts";

export type Product = {
    id: number,
    name: string,
    category: Category,
    price:number
}