export class Penalty{

  constructor(penalty:any) {
    this.id = penalty.id
    this.currency = penalty.currency
    this.description = penalty.description
    this.price = penalty.price
    this.status = penalty.status
    this.idAccuser = penalty.idAccuser
    this.idPenaltyKicker = penalty.idPenaltyKicker
  }

  id:number;
  currency:string;
  description:string;
  price:number;
  status:string;
  idAccuser:number;
  idPenaltyKicker:number;
}
