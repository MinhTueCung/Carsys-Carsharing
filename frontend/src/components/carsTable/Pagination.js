import React from "react"
import classNames from "classnames"
import { map } from "ramda"
import {Button, ButtonGroup} from 'reactstrap'

const PaginationLink = ({ text, isCurrent = false, onClick = null }) =>

    <Button color={"primary"}
        className={classNames("pagination-link", { "is-current": isCurrent })}
      onClick={onClick}
    >
      {text}
    </Button>

export const Pagination = ({ previousPage, canPreviousPage, nextPage, canNextPage, pageOptions, pageIndex, gotoPage }) =>
    <nav className="pagination is-centered" role="navigation" aria-label="pagination">
    <ButtonGroup>
      <Button color="primary" className="pagination-previous" onClick={previousPage} disabled={!canPreviousPage}>Vorherige</Button>
      <Button color="primary" className="pagination-next" onClick={nextPage} disabled={!canNextPage}>Naechste</Button>
    </ButtonGroup>
        <ButtonGroup style={{marginLeft:"1%"}} className="pagination-list">
          {map(page => (
              <PaginationLink
              key={page}
              text={page + 1}
              isCurrent={page === pageIndex}
              onClick={() => gotoPage(page)}
            />
          ), pageOptions)}
        </ButtonGroup>
  </nav>
