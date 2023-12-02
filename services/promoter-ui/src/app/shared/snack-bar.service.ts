import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
  })
export class SnackBarService {

  constructor(private snackBar: MatSnackBar) { }

  public open(message: string): void {
    this.snackBar.open(message, "", {verticalPosition: 'top', duration: 20000})
  }
}
