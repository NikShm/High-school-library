export class Search {
    constructor(sortField: string, sortDirection: string, page: any, pageSize: number) {
        this.sortField = sortField;
        this.sortDirection = sortDirection;
        this.page = page;
        this.pageSize = pageSize;
    }

    sortField : string;
    sortDirection : string;
    page : any;
    pageSize : number;
    searchPattern:any
}

