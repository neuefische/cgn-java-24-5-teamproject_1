
import './App.css'
import ProductView from "./components/ProductView.tsx";
import ShoppingListView from "./components/ShoppingListView.tsx";

export default function App() {

  return (
    <>
        <h1>Grocery</h1>
        <ProductView/>
        <ShoppingListView/>
    </>
  )
}


