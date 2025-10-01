import React from "react";
import { Container, Row, Col, Card } from "react-bootstrap";

const Home: React.FC = () => {
  return (
    <Container className="mt-4">
      <Row>
        <Col>
          <Card>
            <Card.Header>
              <h2 className="mb-0">Welcome to CAMS Web</h2>
              <small className="text-muted">
                Customer Account Management System
              </small>
            </Card.Header>
            <Card.Body>
              <div className="home-content">
                <p className="lead">
                  Lorem ipsum dolor sit amet consectetur adipiscing elit. Sed
                  officiis sit maiores quae non quam harum est sapiente
                  quisquam. Accusantium, voluptatibus. Architecto harum rerum
                  est magnam necessitatibus placeat natus alias dolorem
                  aspernatur. Voluptatum accusamus laudantium nostrum quas
                  doloremque adipisci consequatur eaque magni iure rem delectus
                  maiores, ducimus cupiditate aliquam repellendus voluptas eius
                  iste qui culpa?
                </p>
                <p>
                  Cupiditate veniam quidem et neque qui amet nemo sunt esse
                  consequatur temporibus sapiente illum itaque dignissimos,
                  explicabo perferendis, excepturi ex magnam molestiae delectus
                  vero deserunt, mollitia sed ipsam? Velit minima ea at expedita
                  quisquam alias exercitationem! Omnis molestiae laborum eveniet
                  minus, tenetur harum? Cumque eius facere distinctio laborum
                  dolorum, architecto, accusamus doloribus recusandae ea impedit
                  nulla pariatur voluptatibus.
                </p>
                <p>
                  Asperiores commodi, earum minus veniam delectus pariatur
                  quisquam. Optio tempore velit quo recusandae accusantium
                  asperiores, rem repellat est perferendis quam consectetur!
                  Quaerat enim fugit consectetur perspiciatis, iste recusandae
                  ut porro, molestiae iusto exercitationem ad necessitatibus
                  deserunt! Suscipit ipsam id totam incidunt, vitae quibusdam,
                  voluptates dolorem blanditiis aliquam impedit tempora rerum
                  molestiae explicabo aliquid deserunt amet.
                </p>
                <p>
                  Obcaecati, natus corrupti adipisci, dolorem fuga officia neque
                  necessitatibus nisi optio iure accusamus libero similique sit
                  impedit deleniti? Adipisci quia voluptate eos odio molestias
                  et vel ut cum maiores, incidunt facere veniam.
                </p>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
