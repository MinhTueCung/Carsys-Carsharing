import React, {useMemo} from "react"
import {useExpanded, useGlobalFilter, usePagination, useSortBy, useTable} from "react-table"
import {map} from "ramda"
import {Pagination} from "./Pagination"
import {GlobalFilter} from "./GlobalFilter"
import {FontAwesomeIcon} from "@fortawesome/fontawesome-free"
import {Table} from 'reactstrap'
import './carsReactTable.css'

const ReactTableHead = ({column}) =>
    <div>
        {column.render("Header")}
        {column.isSorted &&
        <span>
        {column.isSortedDesc
            ? <span>
            <FontAwesomeIcon icon="sort-down"/>
          </span>
            : <span>
            <FontAwesomeIcon icon="sort-up"/>
          </span>
        }
      </span>
        }
    </div>

const ReactTableRow = ({row}) =>
    <>
        <tr>
            {map(cell =>
                <td {...cell.getCellProps()}
                    //style={{
                        //padding: '30px',
                        //border: 'solid 5px'
                    //}}
                    >
                    {cell.render("Cell")}
                </td>, row.cells)}
        </tr>
    </>

export const CarsReactTable = ({COLUMNS, DATA, EmptyComponent}) => {
    const columns = useMemo(() => COLUMNS, [COLUMNS])
    const data = useMemo(() => DATA, [DATA])

    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        prepareRow,
        page,
        canPreviousPage,
        canNextPage,
        pageOptions,
        gotoPage,
        nextPage,
        previousPage,
        state: {pageIndex, globalFilter},
        preGlobalFilteredRows,
        setGlobalFilter
    } = useTable(
        {
            columns,
            data
        },
        useGlobalFilter,
        useSortBy,
        useExpanded,
        usePagination
    )

    return (
        <div className="react-table-wrapper">
            <GlobalFilter {...{preGlobalFilteredRows, globalFilter, setGlobalFilter}} />
            <Table {...getTableProps()}>
                <thead>
                {map(headerGroup => (
                    <tr {...headerGroup.getHeaderGroupProps()}>
                        {map(column => (
                            <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                                <ReactTableHead column={column}/>
                            </th>
                        ), headerGroup.headers)}
                    </tr>
                ), headerGroups)}
                </thead>
                <tbody {...getTableBodyProps()}>
                {EmptyComponent
                    ? <tr>
                        <td>{EmptyComponent}</td>
                    </tr>
                    : map(row => {
                        prepareRow(row)
                        return <ReactTableRow row={row}/>
                    }, page)}
                </tbody>
            </Table>
            <Pagination {...{previousPage, canPreviousPage, nextPage, canNextPage, pageOptions, pageIndex, gotoPage}} />
        </div>
    )
}
