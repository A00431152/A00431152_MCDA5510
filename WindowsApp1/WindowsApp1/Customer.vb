Imports System
Imports System.Collections.Generic
Imports System.ComponentModel.DataAnnotations
Imports System.ComponentModel.DataAnnotations.Schema
Imports System.Data.Entity.Spatial

<Table("Customer")>
Partial Public Class Customer
    <DatabaseGenerated(DatabaseGeneratedOption.Identity)>
    Public Property ID As Integer

    Public Property firstName As String
End Class
