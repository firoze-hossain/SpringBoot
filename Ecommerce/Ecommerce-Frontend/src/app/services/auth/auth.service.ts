import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const BASIC_UTL = "http://localhost:8085/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASIC_UTL + "signup", signupRequest);
  }
}
