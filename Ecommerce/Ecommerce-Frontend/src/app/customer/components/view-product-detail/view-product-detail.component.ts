import {Component} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {CustomerService} from "../../services/customer.service";
import {UserStorageService} from "../../../services/storage/user-storage.service";

@Component({
  selector: 'app-view-product-detail',
  templateUrl: './view-product-detail.component.html',
  styleUrls: ['./view-product-detail.component.scss']
})
export class ViewProductDetailComponent {
  productId: number = this.activatedRoute.snapshot.params["productId"];
  product: any;
  FAQS: any[] = [];
  reviews: any[] = [];

  constructor(private snackbar: MatSnackBar,
              private activatedRoute: ActivatedRoute,
              private customerService: CustomerService) {
  }

  ngOnInit(): void {
    this.getProductDetailById();
  }

  getProductDetailById() {
    this.customerService.getProductDetailById(this.productId).subscribe(res => {
      this.product = res.productDto;
      this.product.processedImg = 'data:image/jpeg;base64,' + res.productDto.byteImage;
      this.FAQS = res.faqDtoList;
      res.reviewDtoList.forEach(element => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImg;
        this.reviews.push(element);
      })
    })
  }

  addToWishlist() {
    const wishlistDto= {
      productId: this.productId,
      userId: UserStorageService.getUserId()
    }
    this.customerService.addProductToWishlist(wishlistDto).subscribe(
      res => {
      console.log(res);
      if (res.id != null) {
        this.snackbar.open('Product added to wishlist successfully',
          'Close', {duration: 5000});
      } else {
        this.snackbar.open('Already in wishlist',
          'ERROR', {duration: 5000});
      }
    });
  }
}
