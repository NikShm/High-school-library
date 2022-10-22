import { Injectable } from '@angular/core';
import {Product} from "../models/product";
@Injectable({
  providedIn: 'root'
})
export class FavouriteService {
  productsFav: Product[] = [];
  done: boolean = false;

  addToFavourite(product: Product) {
    this.done = true;
    for(let i = 0; i < this.productsFav.length; i++){
      if (this.productsFav[i].id == product.id){
        this.done = false;
      }
    }

    if (!this.done){
      return this.productsFav;
    }
    else {
      this.productsFav.push(product);
      return localStorage.setItem('productsFav', JSON.stringify(this.productsFav));
    }
  }

  delFromFavourite(index:any) {
    this.productsFav.splice(index, 1);
    localStorage.setItem('productsFav', JSON.stringify(this.productsFav));
    return JSON.parse(localStorage.getItem('productsFav') || '[]');
  }

  getItemsFromFav() {
    return this.productsFav = JSON.parse(localStorage.getItem('productsFav') || '[]');
  }

  clearFavourite() {
    this.productsFav.splice(0, this.productsFav.length);
    return localStorage.removeItem("productsFav");
  }
  constructor() { }
}
