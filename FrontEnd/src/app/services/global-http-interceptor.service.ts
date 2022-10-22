import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GlobalHttpInterceptorService implements HttpInterceptor {
    constructor(private router: Router) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            catchError((error) => {
                let errorMessage = "other error";
                if (error instanceof HttpErrorResponse) {
                    if (error.error instanceof ErrorEvent) {
                        errorMessage = `client side error: ${error.error.message}`;
                    }
                    else {
                        errorMessage = `server error status: ${error.error.status} ${error.statusText}\n${error.error.message}`;
                        switch (error.status) {
                            case 404:
                                if (req.method === 'GET') {
                                    this.router.navigate(['/404']);
                                }
                                else {
                                    window.alert(errorMessage);
                                }
                                break;
                            case 500:
                                errorMessage += "\nPlease try again";
                                window.alert(errorMessage);
                                break;
                        }
                    }
                }
                console.error(errorMessage);
                return throwError(error);
            })
        );
    }
}