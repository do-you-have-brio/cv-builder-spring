import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { cn } from "@/lib/utils";
import { Download, MoreHorizontal, Trash2 } from "lucide-react";
import { useState } from "react";

interface ResumesTableProps {
  resumes: {
    id: string;
    name: string;
    createdAt: string;
    type: string;
    application: string;
  }[];
}

export const ResumesTable = ({ resumes: data }: ResumesTableProps) => {
  const [selectedItems, setSelectedItems] = useState<string[]>([]);

  const handleSelectAll = (checked: boolean) => {
    if (checked) {
      setSelectedItems(data.map((item) => item.id));
    } else {
      setSelectedItems([]);
    }
  };

  const handleSelectItem = (itemId: string, checked: boolean) => {
    if (checked) {
      setSelectedItems((prev) => [...prev, itemId]);
    } else {
      setSelectedItems((prev) => prev.filter((id) => id !== itemId));
    }
  };

  const handleDelete = (itemId: string) => {
    console.log("Delete item:", itemId);
    // Implement delete logic here
  };

  const handleDownload = (itemId: string) => {
    console.log("Download item:", itemId);
    // Implement download logic here
  };

  const handleBulkDelete = () => {
    console.log("Bulk delete items:", selectedItems);
    // Implement bulk delete logic here
    setSelectedItems([]); // Clear selection after action
  };

  const handleBulkDownload = () => {
    console.log("Bulk download items:", selectedItems);
    // Implement bulk download logic here
  };

  const isAllSelected = selectedItems.length === data.length;
  const isIndeterminate =
    selectedItems.length > 0 && selectedItems.length < data.length;

  const hasSelectedItems = selectedItems.length > 0;

  return (
    <div className={cn("flex-1", { "pb-16": hasSelectedItems })}>
      <div className="rounded-md border">
        <Table>
          <TableHeader>
            <TableRow className="sticky top-0 z-50">
              <TableHead className="w-12">
                <Checkbox
                  checked={isAllSelected}
                  onCheckedChange={handleSelectAll}
                  aria-label="Select all"
                  className="data-[state=indeterminate]:bg-primary data-[state=indeterminate]:text-primary-foreground"
                  {...(isIndeterminate && { "data-state": "indeterminate" })}
                />
              </TableHead>
              <TableHead>Name</TableHead>
              <TableHead>Created At</TableHead>
              <TableHead>Type</TableHead>
              <TableHead>Application</TableHead>
              <TableHead className="w-12">Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((item) => (
              <TableRow key={item.id}>
                <TableCell>
                  <Checkbox
                    checked={selectedItems.includes(item.id)}
                    onCheckedChange={(checked) =>
                      handleSelectItem(item.id, checked as boolean)
                    }
                    aria-label={`Select ${item.name}`}
                  />
                </TableCell>
                <TableCell className="font-medium">{item.name}</TableCell>
                <TableCell>{item.createdAt}</TableCell>
                <TableCell>
                  <Badge variant="secondary">{item.type}</Badge>
                </TableCell>
                <TableCell>{item.application}</TableCell>
                <TableCell>
                  <DropdownMenu>
                    <DropdownMenuTrigger asChild>
                      <Button variant="ghost" className="h-8 w-8 p-0">
                        <span className="sr-only">Open menu</span>
                        <MoreHorizontal className="h-4 w-4" />
                      </Button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent align="end">
                      <DropdownMenuItem onClick={() => handleDownload(item.id)}>
                        <Download className="mr-2 h-4 w-4" />
                        Download
                      </DropdownMenuItem>
                      <DropdownMenuItem
                        onClick={() => handleDelete(item.id)}
                        className="text-destructive"
                      >
                        <Trash2 className="mr-2 h-4 w-4" />
                        Delete
                      </DropdownMenuItem>
                    </DropdownMenuContent>
                  </DropdownMenu>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>

      {hasSelectedItems && (
        <div className="flex items-center justify-between p-4 bg-muted rounded-md flex-wrap fixed bottom-0 inset-x-0 z-50 mx-auto max-w-3xl">
          <span className="text-sm text-muted-foreground">
            {selectedItems.length} item{selectedItems.length !== 1 ? "s" : ""}{" "}
            selected
          </span>
          <div className="space-x-2">
            <Button variant="outline" size="sm" onClick={handleBulkDownload}>
              Bulk Download
            </Button>
            <Button variant="destructive" size="sm" onClick={handleBulkDelete}>
              Bulk Delete
            </Button>
          </div>
        </div>
      )}
    </div>
  );
};
