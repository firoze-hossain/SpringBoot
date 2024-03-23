import {Component} from '@angular/core';
import {AdminService} from "../../service/admin.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-post-product-faq',
  templateUrl: './post-product-faq.component.html',
  styleUrls: ['./post-product-faq.component.scss']
})
export class PostProductFaqComponent {
  productId: number = this.activatedRoute.snapshot.params['productId'];
  FAQForm!: FormGroup;

  constructor(private adminService: AdminService,
              private fb: FormBuilder,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.FAQForm = this.fb.group({
      question: [null, [Validators.required]],
      answer: [null, [Validators.required]]
    })
  }

  postFAQ() {
    this.adminService.postFAQ(this.productId, this.FAQForm.value).subscribe(res => {
      if (res.id != null) {
        this.snackbar.open('FAQ posted successfully', 'Close', {duration: 50000});
        this.router.navigateByUrl('/admin/dashboard');
      } else {
        this.snackbar.open('Something went wrong', 'Close', {duration: 50000, panelClass: 'error-snackbar'});
      }
    })
  }

}
