import { ResumesTable } from "@/components/resumes-table";

const resumesDataMock = [
  {
    id: 1,
    name: "User Authentication Module",
    createdAt: "2024-01-15",
    type: "Component",
    applicationName: "E-commerce Platform",
  },
  {
    id: 2,
    name: "Payment Gateway Integration",
    createdAt: "2024-01-12",
    type: "Service",
    applicationName: "Online Store",
  },
  {
    id: 3,
    name: "Dashboard Analytics",
    createdAt: "2024-01-10",
    type: "Page",
    applicationName: "Admin Panel",
  },
  {
    id: 4,
    name: "Email Notification System",
    createdAt: "2024-01-08",
    type: "Service",
    applicationName: "CRM System",
  },
  {
    id: 5,
    name: "Product Catalog",
    createdAt: "2024-01-05",
    type: "Component",
    applicationName: "E-commerce Platform",
  },
  {
    id: 6,
    name: "User Profile Management",
    createdAt: "2024-01-03",
    type: "Page",
    applicationName: "Social Media App",
  },
  {
    id: 7,
    name: "File Upload Handler",
    createdAt: "2024-01-01",
    type: "Utility",
    applicationName: "Document Manager",
  },
  {
    id: 8,
    name: "Search Functionality",
    createdAt: "2023-12-28",
    type: "Feature",
    applicationName: "Knowledge Base",
  },
];

export const ResumesPage = () => {
  // @todo: implement resumes table

  return (
    <div className="flex flex-col">
      <ResumesTable resumes={resumesDataMock} />
    </div>
  );
};
