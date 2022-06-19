import { Injectable } from "@angular/core";
import { environment } from "./../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable } from "rxjs";
import { Cliente } from "./../models/cliente";

@Injectable({
  providedIn: "root",
})
export class ClienteService {
  baseURL: String = environment.baseURL;

  constructor(private http: HttpClient, private snack: MatSnackBar) {}

  findAll(): Observable<Cliente[]> {
    const url = this.baseURL + "/clientes";
    return this.http.get<Cliente[]>(url);
  }

  findById(id: any): Observable<Cliente> {
    const url = `${this.baseURL}/clientes/${id}`;
    return this.http.get<Cliente>(url);
  }

  create(cliente: Cliente): Observable<Cliente> {
    const url = this.baseURL + "/clientes";
    return this.http.post<Cliente>(url, cliente);
  }

  update(cliente: Cliente): Observable<Cliente> {
    const url = `${this.baseURL}/clientes/${cliente.id}`;
    return this.http.put<Cliente>(url, cliente);
  }

  message(msg: String): void {
    this.snack.open(`${msg}`, "OK", {
      horizontalPosition: "end",
      verticalPosition: "top",
      duration: 4000,
    });
  }
}