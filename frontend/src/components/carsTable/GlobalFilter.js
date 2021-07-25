import React, {useState} from "react"
import {useAsyncDebounce} from "react-table"

export const GlobalFilter = ({globalFilter, setGlobalFilter}) => {
    const [value, setValue] = useState(globalFilter)

    const handleChange = useAsyncDebounce(value => {
        setGlobalFilter(value || undefined)
    }, 200)

    return (
        <div className="flex">
            <div>
                <input
                    type="text"
                    value={value || ""}
                    onChange={e => {
                        setValue(e.target.value)
                        handleChange(e.target.value)
                    }}
                    placeholder="Suche"
                />
            </div>
        </div>
    )
}