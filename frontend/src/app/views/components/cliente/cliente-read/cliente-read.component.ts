import { Component, ViewChild } from "@angular/core";
import { AfterViewInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { Router } from "@angular/router";
import { Cliente } from "./../../../../models/cliente";
import { ClienteService } from "./../../../../services/cliente.service";

@Component({
  selector: "app-cliente-read",
  templateUrl: "./cliente-read.component.html",
  styleUrls: ["./cliente-read.component.css"],
})
export class ClienteReadComponent implements AfterViewInit {
  clientes: Cliente[] = [];

  displayedColumns: string[] = ["id", "nome", "cpf", "telefone", "action"];
  dataSource = new MatTableDataSource<Cliente>(this.clientes);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) ordenar!: MatSort;

  constructor(private service: ClienteService, private router: Router) {}

  ngAfterViewInit() {
    this.findAll();

    this.paginator._intl.itemsPerPageLabel = "Itens por página";
    this.paginator._intl.firstPageLabel = "Página inicial";
    this.paginator._intl.lastPageLabel = "Última página";
    this.paginator._intl.previousPageLabel = "Página anterior";
    this.paginator._intl.nextPageLabel = "Próxima página";
    this.paginator._intl.getRangeLabel = (
      page: number,
      pageSize: number,
      length: number
    ) => {
      if (length === 0 || pageSize === 0) {
        return `0 de ${length}`;
      }
      if (pageSize >= length) {
        return `1 de 1`;
      }
      length = Math.max(length, 0);
      const startIndex = page * pageSize;

      startIndex < length
        ? Math.min(startIndex + pageSize, length)
        : startIndex + pageSize;
      return `${startIndex + 1} de ${length}`;
    };
  }

  findAll(): void {
    this.service.findAll().subscribe((resposta) => {
      this.clientes = resposta;
      // Teste:
      // console.log(this.tecnicos);
      this.dataSource = new MatTableDataSource<Cliente>(this.clientes);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.ordenar;
    });
  }

  navigateToCreate(): void {
    this.router.navigate(["/clientes/create"]);
  }
}
