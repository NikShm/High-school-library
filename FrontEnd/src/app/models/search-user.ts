export class SearchUser {
    constructor(search: any, sortField: string, sortDirection: string, page: any, pageSize: number) {
        this.search = search;
        this.sortField = sortField;
        this.sortDirection = sortDirection;
        this.page = page;
        this.pageSize = pageSize;
    }

    search : any;
    sortField : string;
    sortDirection : string;
    page : any;
    pageSize : number;
}
