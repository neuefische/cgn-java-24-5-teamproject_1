import React, { useState } from "react";
import './SearchBar.css';

type SearchBarProps = {
    onSearch: (query: string) => void;
};

const SearchBar = ({ onSearch }: SearchBarProps) => {
    const [query, setQuery] = useState('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        setQuery(value);

        const queryToSearch =value.toLowerCase();
        onSearch(queryToSearch);
    };

    return (
        <div className="search-bar">
            <input
                type="text"
                placeholder="Search products..."
                value={query}
                onChange={handleChange}
                className="search-input"
            />
        </div>
    );
};

export default SearchBar;