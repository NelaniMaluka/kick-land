import { useParams } from "react-router-dom";

function Info() {
  const { page } = useParams();

  return <div>{page}</div>;
}

export default Info;
