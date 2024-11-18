import {Category} from "./Category.ts";
import {Status} from "./Status.ts";

export type Product = {
    id: number,
    name: string,
    category: Category,
    price: number,
    count: number,
    status: Status,
    image: string
}