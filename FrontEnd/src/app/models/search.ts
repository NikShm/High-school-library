export class Search {
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

export class SearchAuthorsBook extends Search{


  constructor(search: any, sortField: string, sortDirection: string, page: any, pageSize: number, authorId: number | null) {
    super(search, sortField, sortDirection, page, pageSize);
    this.authorId = authorId;
  }

  authorId : number|null;
}
