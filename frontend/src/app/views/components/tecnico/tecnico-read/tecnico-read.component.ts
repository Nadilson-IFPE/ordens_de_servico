import { AfterViewInit, Component, ViewChild } from "@angular/core";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { Tecnico } from "src/app/models/tecnico";
import { TecnicoService } from "src/app/services/tecnico.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-tecnico-read",
  templateUrl: "./tecnico-read.component.html",
  styleUrls: ["./tecnico-read.component.css"],
})
export class TecnicoReadComponent implements AfterViewInit {
  tecnicos: Tecnico[] = [];

  displayedColumns: string[] = ["id", "nome", "cpf", "telefone"];
  dataSource = new MatTableDataSource<Tecnico>(this.tecnicos);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) ordenar!: MatSort;

  constructor(private service: TecnicoService, private router: Router) {}

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
      if (pageSize == length || pageSize >= length) {
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
      this.tecnicos = resposta;
      // Teste:
      // console.log(this.tecnicos);
      this.dataSource = new MatTableDataSource<Tecnico>(this.tecnicos);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.ordenar;
    });
  }

  navigateToCreate(): void {
    this.router.navigate(["/tecnicos/create"]);
  }
}
